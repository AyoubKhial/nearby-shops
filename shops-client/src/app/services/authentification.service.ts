import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

/**
 * a service which talk to the backend's authentification services.
 */
@Injectable({
    providedIn: 'root'
})
export class AuthentificationService {

    private apiUrl: string = 'http://localhost:8080/api/v1/users';
    private config: object = { headers: new HttpHeaders().set('Content-Type', 'application/json') };

    constructor(private httpClient: HttpClient) { }

    register(user): Observable<any> {
        return this.httpClient.post(this.apiUrl + "/signup", user, this.config)
            .pipe(map(response => {
                return response;
            }));
    }

    login(user): Observable<any> {
        return this.httpClient.post(this.apiUrl + "/signin", user, this.config)
            .pipe(map(response => {
                return response;
            }));
    }
}
