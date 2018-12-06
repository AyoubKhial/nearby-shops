import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { TokenService } from './token.service';
import { ShopsPage } from '../models/shops-page';

@Injectable({
    providedIn: 'root'
})
export class ShopsService {

    private apiUrl: string = 'http://localhost:8080/api/v1/shops';

    constructor(private httpClient: HttpClient, private tokenService: TokenService) { }

    getAllShops(location): Observable<ShopsPage> {
        var config = { headers: { Authorization: this.tokenService.getToken() }}
        return this.httpClient.get<ShopsPage>(this.apiUrl + "?longitude=" + location.longitude + "&latitude=" + location.latitude, config)
            .pipe(map(response => {
                return response;
            }));
    }
}
