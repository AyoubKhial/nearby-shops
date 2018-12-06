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

    constructor(private httpClient: HttpClient, private tokenService: TokenService) { }

    getAllShops(location): Observable<ShopsPage> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Authorization': this.tokenService.getToken()
            })
        };
        return this.httpClient.get<ShopsPage>(this.apiUrl + "?longitude=" + location.longitude + "&latitude=" + location.latitude, httpOptions)
            .pipe(map(response => {
                return response;
            }));
    }

    getPreferredShops(): Observable<ShopsPage> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Authorization': this.tokenService.getToken()
            })
        };
        return this.httpClient.get<ShopsPage>(this.apiUrl + "/liked", httpOptions)
            .pipe(map(response => {
                return response;
            }));
    }

    addShopToPreferred(shopId: string): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': this.tokenService.getToken()
            })
        };
        return this.httpClient.post(this.apiUrl + "/like?shop=" + shopId, httpOptions)
            .pipe(map(response => {
                return response;
            }));
    }

    addShopToDisliked(shopId: string): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': this.tokenService.getToken()
            })
        };
        return this.httpClient.post(this.apiUrl + "/dislike?shop=" + shopId, httpOptions)
            .pipe(map(response => {
                return response;
            }));
    }

    removeShopeFromPreferred(shopId: string): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Authorization': this.tokenService.getToken()
            })
        };
        return this.httpClient.delete(this.apiUrl + "/like/undo?shop=" + shopId, httpOptions)
            .pipe(map(response => {
                return response;
            }));
    }

    removeShopeFromDisliked(shopId: string): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Authorization': this.tokenService.getToken()
            })
        };
        return this.httpClient.delete(this.apiUrl + "/dislike/undo?shop=" + shopId, httpOptions)
            .pipe(map(response => {
                return response;
            }));
    }
}
