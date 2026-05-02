import { Component, inject, OnInit, signal } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from '../../../shared/components/alert/alert.service';
import { EventService } from '../event.service';
import { EventWithFullData } from '../../../models/event/Event';

@Component({
  selector: 'app-event-list',
  imports: [],
  templateUrl: './event-list.component.html',
  styleUrl: './event-list.component.scss',
})
export class EventListComponent implements OnInit {
  private router = inject(Router);
  private alertService = inject(AlertService);
  private eventService = inject(EventService);

  protected events = signal<EventWithFullData[]>([]);
  protected openEventId = signal<string | null>(null);

  ngOnInit(): void {
    this.load();
  }

  private load(): void {
    this.eventService.getAllWithFullData().subscribe({
      next: (data) => this.events.set(data),
      error: () => this.alertService.error('Failed to load events'),
    });
  }

  protected toggle(id: string): void {
    this.openEventId.set(this.openEventId() === id ? null : id);
  }

  protected isOpen(id: string): boolean {
    return this.openEventId() === id;
  }

  protected delete(id: string, event: MouseEvent): void {
    event.stopPropagation();
    this.eventService.deleteEvent(id).subscribe({
      next: () => {
        this.alertService.success('Event deleted');
        if (this.openEventId() === id) this.openEventId.set(null);
        this.load();
      },
      error: () => this.alertService.error('Failed to delete event'),
    });
  }

  protected goToCreator(): void {
    this.router.navigate(['/event-creator']);
  }

  protected goToManager(id: string, event: MouseEvent): void {
    event.stopPropagation();
    this.router.navigate(['/event-manager', id]);
  }
}
