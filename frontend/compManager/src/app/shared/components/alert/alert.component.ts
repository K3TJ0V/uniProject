import { Component, inject } from '@angular/core';
import { AlertService } from './alert.service';
import { AsyncPipe } from '@angular/common';

@Component({
    selector: 'app-alert',
    imports: [AsyncPipe],
    templateUrl: './alert.component.html',
    styleUrl: './alert.component.scss',
})
export class AlertComponent {
    private service = inject(AlertService);
    protected queue$ = this.service.queue;
}
