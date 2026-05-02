import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../../../shared/components/alert/alert.service';
import { UserService } from '../../users/user.service';
import { EventService } from '../event.service';
import { JudgeResponse } from '../../../models/user/Judge';
import { CompetitorResponse } from '../../../models/user/Competitor';

@Component({
  selector: 'app-event-creator',
  imports: [ReactiveFormsModule],
  templateUrl: './event-creator.component.html',
  styleUrl: './event-creator.component.scss',
})
export class EventCreatorComponent implements OnInit {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private alertService = inject(AlertService);
  private userService = inject(UserService);
  private eventService = inject(EventService);

  protected judges = signal<JudgeResponse[]>([]);
  protected competitors = signal<CompetitorResponse[]>([]);
  protected selectedJudgeIds = signal(new Set<string>());
  protected selectedCompetitorIds = signal(new Set<string>());

  protected form = this.fb.group({
    name: ['', Validators.required],
    rank: ['', Validators.required],
    location: ['', Validators.required],
    startDate: ['', Validators.required],
  });

  ngOnInit(): void {
    this.userService.getAllJudges().subscribe({
      next: (data) => this.judges.set(data),
      error: () => this.alertService.error('Failed to load judges'),
    });
    this.userService.getAllCompetitor().subscribe({
      next: (data) => this.competitors.set(data),
      error: () => this.alertService.error('Failed to load competitors'),
    });
  }

  protected toggleJudge(id: string): void {
    const next = new Set(this.selectedJudgeIds());
    next.has(id) ? next.delete(id) : next.add(id);
    this.selectedJudgeIds.set(next);
  }

  protected toggleCompetitor(id: string): void {
    const next = new Set(this.selectedCompetitorIds());
    next.has(id) ? next.delete(id) : next.add(id);
    this.selectedCompetitorIds.set(next);
  }

  protected isJudgeSelected(id: string): boolean {
    return this.selectedJudgeIds().has(id);
  }

  protected isCompetitorSelected(id: string): boolean {
    return this.selectedCompetitorIds().has(id);
  }

  protected submit(): void {
    if (this.form.invalid) return;
    const raw = this.form.getRawValue();

    this.eventService.addEvent({
      name: raw.name!,
      rank: raw.rank!,
      location: raw.location!,
      startDate: raw.startDate!,
      judges: Array.from(this.selectedJudgeIds()),
      competitors: Array.from(this.selectedCompetitorIds()),
    }).subscribe({
      next: () => {
        this.alertService.success('Event created successfully');
        this.router.navigate(['/events']);
      },
      error: () => this.alertService.error('Failed to create event'),
    });
  }

  protected cancel(): void {
    this.router.navigate(['/events']);
  }
}
