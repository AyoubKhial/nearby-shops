import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Shop } from '../components/models/shop';
import { TokenService } from './token.service';
import { ShopsPage } from '../components/models/shops-page';

@Injectable({
    providedIn: 'root'
})
export class ShopsService {

    private apiUrl: string = 'http://localhost:8080/api/v1/shops';

    constructor(private httpClient: HttpClient, private tokenService: TokenService) {

    }

    getAllShops(location): Observable<ShopsPage> {
        var x = { headers: { Authorization: this.tokenService.getToken() }}
        return this.httpClient.get<ShopsPage>(this.apiUrl + "?longitude=" + location.longitude + "&latitude=" + location.latitude, x)
            .pipe(map(response => {
                return response;
            }));
    }
}
