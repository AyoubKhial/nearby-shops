import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { TokenService } from './token.service';
import { ShopsPage } from '../models/shops-page';

@Injectable({
    providedIn: 'root'
})
export class ShopsService {

    private apiUrl: string = 'http://localhost:8080/api/v1/shops';
    private config = { headers: { Authorization: this.tokenService.getToken() }}

    constructor(private httpClient: HttpClient, private tokenService: TokenService) { }

    getAllShops(location): Observable<ShopsPage> {
        return this.httpClient.get<ShopsPage>(this.apiUrl + "?longitude=" + location.longitude + "&latitude=" + location.latitude, this.config)
            .pipe(map(response => {
                return response;
            }));
    }

    getPreferredShops(): Observable<ShopsPage> {
        return this.httpClient.get<ShopsPage>(this.apiUrl + "/liked", this.config)
            .pipe(map(response => {
                return response;
            }));
    }

    removeShopeFromPreferred(shopId: string): Observable<any> {
        return this.httpClient.post(this.apiUrl + "/like/undo?shop=" + shopId, this.config)
            .pipe(map(response => {
                return response;
            }));
    }

    removeShopeFromDisliked(shopId: string): Observable<any> {
        return this.httpClient.post(this.apiUrl + "/dislike/undo?shop=" + shopId, this.config)
            .pipe(map(response => {
                return response;
            }));
    }

    addShopToPreferred(shopId: string): Observable<any> {
        return this.httpClient.get(this.apiUrl + "/like?shop=" + shopId, this.config)
            .pipe(map(response => {
                return response;
            }));
    }

    addShopToDisliked(shopId: string): Observable<any> {
        return this.httpClient.get(this.apiUrl + "/dislike?shop=" + shopId, this.config)
            .pipe(map(response => {
                return response;
            }));
    }
}
