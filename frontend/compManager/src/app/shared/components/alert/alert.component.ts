import { Component, inject, OnDestroy } from '@angular/core';
import { AlertService } from './alert.service';
import { AsyncPipe } from '@angular/common';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-alert',
  imports: [AsyncPipe],
  templateUrl: './alert.component.html',
  styleUrl: './alert.component.scss',
})
export class AlertComponent implements OnDestroy {
    private service = inject(AlertService);
    private subs = new Subscription();
    protected values$ = this.service.queue; 

    ngOnDestroy(): void {
        this.subs.unsubscribe();
    }
}
