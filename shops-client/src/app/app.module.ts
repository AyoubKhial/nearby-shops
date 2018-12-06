import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';

import { AuthentificationService } from './services/authentification.service';
import { TokenService } from './services/token.service';
import { ShopsListComponent } from './components/shops-list/shops-list.component';
import { ShopsService } from './services/shops.service';
import { AuthGuardService } from './services/auth-guard.service';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        ShopsListComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        ReactiveFormsModule
    ],
    providers: [
        AuthentificationService,
        TokenService,
        ShopsService,
        AuthGuardService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
