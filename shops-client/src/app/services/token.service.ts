import { Injectable } from '@angular/core';

/**
 * service for the token manipulation
 */
@Injectable({
    providedIn: 'root'
})
export class TokenService {

    private tokenType: string = "Bearer";
    private accessToken: string;

    constructor() { }

    // Create a token for the user
    saveToken(token: string): void {
        localStorage.setItem('token', this.tokenType + " " + token);
        this.accessToken = this.tokenType + " " + token;
    }

    getToken(): string {
        if (!this.accessToken) {
            this.accessToken = localStorage.getItem('token');
        }
        return this.accessToken;
    }
}
