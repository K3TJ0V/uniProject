import { inject, Injectable } from "@angular/core";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { EventRequest, EventResponse, EventWithFullData } from "../../models/event/Event";

const baseUrl = 'http://localhost:8080/api';

@Injectable({ providedIn: 'root' })
export class EventService {
    private http = inject(HttpClient);

    private events = new BehaviorSubject<EventResponse[]>([]);
    public events$ = this.events.asObservable();

    public getAll(): Observable<EventResponse[]> {
        return this.http.get<EventResponse[]>(`${baseUrl}/event`)
            .pipe(tap(data => this.events.next(data)));
    }

    public addEvent(event: EventRequest): Observable<EventResponse> {
        return this.http.post<EventResponse>(`${baseUrl}/event`, event)
            .pipe(tap(() => this.getAll().subscribe()));
    }

    public updateEvent(id: string, event: EventRequest): Observable<EventResponse> {
        return this.http.put<EventResponse>(`${baseUrl}/event/${id}`, event)
            .pipe(tap(() => this.getAll().subscribe()));
    }

    public deleteEvent(id: string): Observable<void> {
        return this.http.delete<void>(`${baseUrl}/event/${id}`)
            .pipe(tap(() => this.getAll().subscribe()));
    }

    public getAllWithFullData(): Observable<EventWithFullData[]> {
        return this.http.get<EventWithFullData[]>(`${baseUrl}/event/full`);
    }

    public getEventWithFullData(id: string): Observable<EventWithFullData> {
        return this.http.get<EventWithFullData>(`${baseUrl}/event/${id}/full`);
    }
}
