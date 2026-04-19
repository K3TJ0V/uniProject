import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

export interface alertState {
    id: number,
    message: string,
    type: 'error' | 'success' | 'info',
}

@Injectable({ providedIn: 'root' })
export class AlertService {
    private nextId = 1;
    private state = new BehaviorSubject<alertState[]>([]);
    public queue = this.state.asObservable();

    private add(message: string, type: alertState['type']) {
        const currentId = this.nextId++;
        this.state.next([...this.state.value, { id: currentId, message, type }]);
        setTimeout(() => {
            this.state.next(this.state.value.filter(alert => alert.id !== currentId));
        }, 2000);
    }

    public info(message: string) { this.add(message, 'info'); }
    public error(message: string) { this.add(message, 'error'); }
    public success(message: string) { this.add(message, 'success'); }
}