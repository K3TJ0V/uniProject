import { inject, Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { AttemptRequest, AttemptResponse } from "../../models/Attempt";

const baseUrl = 'http://localhost:8080/api';

@Injectable({ providedIn: 'root' })
export class AttemptService {
    private http = inject(HttpClient);

    getByEvent(eventId: string): Observable<AttemptResponse[]> {
        return this.http.get<AttemptResponse[]>(`${baseUrl}/event/${eventId}/attempts`);
    }

    save(eventId: string, attempt: AttemptRequest): Observable<AttemptResponse> {
        return this.http.post<AttemptResponse>(`${baseUrl}/event/${eventId}/attempts`, attempt);
    }
}
