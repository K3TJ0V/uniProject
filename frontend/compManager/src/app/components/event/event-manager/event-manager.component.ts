import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AlertService } from '../../../shared/components/alert/alert.service';
import { EventService } from '../event.service';
import { AttemptService } from '../attempt.service';
import { EventWithFullData } from '../../../models/event/Event';
import { AttemptResponse, DecisionRequest, Discipline } from '../../../models/Attempt';
import { JudgeResponse } from '../../../models/user/Judge';
import { CompetitorResponse } from '../../../models/user/Competitor';

interface ActiveSlot {
    competitorId: string;
    discipline: Discipline;
    attemptNumber: 1 | 2 | 3;
    weight: string;
    decisions: { judgeId: string; judgeName: string; valid: boolean }[];
}

@Component({
    selector: 'app-event-manager',
    imports: [FormsModule],
    templateUrl: './event-manager.component.html',
    styleUrl: './event-manager.component.scss',
})
export class EventManagerComponent implements OnInit {
    private route = inject(ActivatedRoute);
    private router = inject(Router);
    private alertService = inject(AlertService);
    private eventService = inject(EventService);
    private attemptService = inject(AttemptService);

    protected event = signal<EventWithFullData | null>(null);
    protected attempts = signal<AttemptResponse[]>([]);
    protected activeSlot = signal<ActiveSlot | null>(null);

    protected readonly disciplines: Discipline[] = ['SNATCH', 'CLEAN_AND_JERK'];
    protected readonly attemptNumbers = [1, 2, 3] as const;

    protected disciplineLabel = (d: Discipline) =>
        d === 'SNATCH' ? 'Snatch' : 'Clean & Jerk';

    private get eventId(): string {
        return this.route.snapshot.paramMap.get('id')!;
    }

    ngOnInit(): void {
        this.loadEvent();
        this.loadAttempts();
    }

    private loadEvent(): void {
        this.eventService.getEventWithFullData(this.eventId).subscribe({
            next: (data) => this.event.set(data),
            error: () => this.alertService.error('Failed to load event'),
        });
    }

    private loadAttempts(): void {
        this.attemptService.getByEvent(this.eventId).subscribe({
            next: (data) => this.attempts.set(data),
            error: () => this.alertService.error('Failed to load attempts'),
        });
    }

    protected getAttempt(
        competitorId: string,
        discipline: Discipline,
        attemptNumber: number,
    ): AttemptResponse | undefined {
        return this.attempts().find(
            (a) =>
                a.competitorId === competitorId &&
                a.discipline === discipline &&
                a.attemptNumber === attemptNumber,
        );
    }

    protected openSlot(
        competitor: CompetitorResponse,
        discipline: Discipline,
        attemptNumber: 1 | 2 | 3,
    ): void {
        const ev = this.event();
        if (!ev) return;

        const existing = this.getAttempt(competitor.id, discipline, attemptNumber);

        const decisions = ev.judges.map((j: JudgeResponse) => {
            const existingDecision = existing?.decisions.find((d) => d.judgeId === j.id);
            return {
                judgeId: j.id,
                judgeName: `${j.firstName} ${j.lastName}`,
                valid: existingDecision?.valid ?? false,
            };
        });

        this.activeSlot.set({
            competitorId: competitor.id,
            discipline,
            attemptNumber,
            weight: existing?.declaredWeight?.toString() ?? '',
            decisions,
        });
    }

    protected closeSlot(): void {
        this.activeSlot.set(null);
    }

    protected isActiveSlot(
        competitorId: string,
        discipline: Discipline,
        attemptNumber: number,
    ): boolean {
        const s = this.activeSlot();
        return (
            s !== null &&
            s.competitorId === competitorId &&
            s.discipline === discipline &&
            s.attemptNumber === attemptNumber
        );
    }

    protected toggleDecision(judgeId: string): void {
        const slot = this.activeSlot();
        if (!slot) return;
        this.activeSlot.set({
            ...slot,
            decisions: slot.decisions.map((d) =>
                d.judgeId === judgeId ? { ...d, valid: !d.valid } : d,
            ),
        });
    }

    protected saveSlot(): void {
        const slot = this.activeSlot();
        if (!slot) return;

        const weight = parseFloat(slot.weight);
        if (isNaN(weight) || weight <= 0) {
            this.alertService.error('Enter a valid weight');
            return;
        }

        const decisions: DecisionRequest[] = slot.decisions.map((d) => ({
            judgeId: d.judgeId,
            valid: d.valid,
        }));

        this.attemptService
            .save(this.eventId, {
                competitorId: slot.competitorId,
                discipline: slot.discipline,
                attemptNumber: slot.attemptNumber,
                declaredWeight: weight,
                decisions,
            })
            .subscribe({
                next: (saved) => {
                    this.attempts.update((list) => {
                        const idx = list.findIndex(
                            (a) =>
                                a.competitorId === saved.competitorId &&
                                a.discipline === saved.discipline &&
                                a.attemptNumber === saved.attemptNumber,
                        );
                        return idx >= 0
                            ? list.map((a, i) => (i === idx ? saved : a))
                            : [...list, saved];
                    });
                    this.activeSlot.set(null);
                },
                error: () => this.alertService.error('Failed to save attempt'),
            });
    }

    protected hasActiveSlot(competitorId: string, discipline: Discipline): boolean {
        const s = this.activeSlot();
        return s !== null && s.competitorId === competitorId && s.discipline === discipline;
    }

    protected bestLift(competitorId: string, discipline: Discipline): number | null {
        const valid = this.attempts().filter(
            (a) => a.competitorId === competitorId && a.discipline === discipline && a.result === 'VALID',
        );
        if (valid.length === 0) return null;
        return Math.max(...valid.map((a) => a.declaredWeight ?? 0));
    }

    protected updateSlotWeight(value: string): void {
        const slot = this.activeSlot();
        if (slot) this.activeSlot.set({ ...slot, weight: value });
    }

    protected back(): void {
        this.router.navigate(['/events']);
    }
}
