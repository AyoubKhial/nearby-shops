import { Component } from '@angular/core';
import { Router, ActivatedRoute, RouterStateSnapshot } from '@angular/router';
import { TokenService } from './services/token.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    

    constructor(private tokenService: TokenService, private router: Router) { }

    logout() {
        this.tokenService.removeToken();
        this.router.navigate(['/login']);
    }
}
