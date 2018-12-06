import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';
import { Subject } from 'rxjs';
import { takeUntil } from "rxjs/operators";
import { Router } from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

    public activeFormLogin: boolean;

    // register form variables
    public registerForm: FormGroup;
    public registerEmail: FormControl;
    public registerPassword: FormControl;
    public confirmPassword: FormControl;

    // login form variables
    public loginForm: FormGroup;
    public loginEmail: FormControl;
    public loginPassword: FormControl;

    // error and success message variables
    public passwordMatch: boolean = true;
    public badRequestError: string = "";
    public successfulRegister: boolean = false;

    private unsubscribe = new Subject<void>();

    constructor(private authentificationService: AuthentificationService, private tokenService: TokenService, private router: Router) {
        if(tokenService.getToken()) {
            this.router.navigate(['/home']);
        }
    }

    ngOnInit() {
        // login form appears first
        this.activeFormLogin = true;
        // initialize the register form
        this.createRegisterFormControls();
        this.createRegisterForm();
        // initialize the login form
        this.createLoginFormControls();
        this.createLoginForm();
    }

    // initialize the register form controls
    createRegisterFormControls(): void {
        // initialize the email control with required and should be email.
        this.registerEmail = new FormControl('', [
            Validators.required,
            Validators.email
        ]);
        // initialize the password controls with required and min length set to 8.
        this.registerPassword = new FormControl('', [
            Validators.required,
            Validators.minLength(8),
        ]);
        // initialize the confirmPassword controls with with required.
        this.confirmPassword = new FormControl('', [
            Validators.required
        ]);
    }

    // initialize the register form group.
    createRegisterForm(): void {
        this.registerForm = new FormGroup({
            registerEmail: this.registerEmail,
            registerPassword: this.registerPassword,
            confirmPassword: this.confirmPassword
        });
    }

    // initialize the login form controls
    createLoginFormControls(): void {
        // initialize the email control with required and should be email.
        this.loginEmail = new FormControl('', [
            Validators.required,
            Validators.email
        ]);
        // initialize the password controls with required and min length set to 8.
        this.loginPassword = new FormControl('', [
            Validators.required,
            Validators.minLength(8),
        ]);
    }

    // initialize the login form group.
    createLoginForm(): void {
        this.loginForm = new FormGroup({
            loginEmail: this.loginEmail,
            loginPassword: this.loginPassword
        });
    }

    // toogle forms from login to register and vice versa
    toggleForms() {
        this.activeFormLogin = !this.activeFormLogin;
    }

    // register a user
    register(): void {
        // reinitialize message variables
        this.passwordMatch = true;
        this.badRequestError = "";
        // do nothing if the form is invalid
        if (this.registerForm.invalid) {
            return;
        }
        // chack if the password and the confirmation matchs
        if (this.registerPassword.value != this.confirmPassword.value) {
            this.passwordMatch = false;
            return;
        }
        // create a json which will be send to the backend
        var user = {
            email: this.registerEmail.value,
            password: this.registerPassword.value
        }
        // make an http call and check the returned result
        this.authentificationService.register(user).pipe(takeUntil(this.unsubscribe)).subscribe(response => {
            if (response.code == 201) {
                this.activeFormLogin = true;
                this.successfulRegister = true;
            }
        },
        error => {
            this.badRequestError = error.error.message;
        });
    }

    // user login
    login(): void {
        // reinitialize message variables
        this.successfulRegister = false;
        this.badRequestError = "";
        // do nothing if the form is invalid
        if (this.loginForm.invalid) {
            return;
        }
        // create a json which will be send to the backend
        var user = {
            email: this.loginEmail.value,
            password: this.loginPassword.value
        }
        // make an http call and check the returned result
        this.authentificationService.login(user).pipe(takeUntil(this.unsubscribe)).subscribe(response => {
            this.tokenService.saveToken(response.accessToken);
            this.router.navigate(['/home']);
        },
        error => {
            this.badRequestError = error.error.message;
        });
    }

    // unsubscribing from observable subscriptions 
    ngOnDestroy(): void {
        this.unsubscribe.next();
        this.unsubscribe.complete();
    }
}