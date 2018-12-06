import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ShopsListComponent } from './components/shops-list/shops-list.component';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'home', component: ShopsListComponent, canActivate: [AuthGuardService]  }
];

@NgModule({
    declarations: [],
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
	  	RouterModule
	]
})
export class AppRoutingModule { }
