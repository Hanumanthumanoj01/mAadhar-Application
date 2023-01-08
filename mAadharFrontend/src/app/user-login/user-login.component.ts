import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css'],
})
export class UserLoginComponent implements OnInit {
  citizenId: string = '';
  password: string = '';
  status: string = '';
  isDisabled = true;
  cssStringVar: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    public authService: AuthService
  ) {}

  submitted = false;
  logInForm: FormGroup = new FormGroup({});

  ngOnInit(): void {
    this.logInForm = this.formBuilder.group({
      citizenId: ['', [Validators.required]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(10),
          Validators.min(999999999),
          Validators.max(9999999999),
        ],
      ],
    });
  }

  get f() {
    return this.logInForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.logInForm.invalid) {
      return;
    }
    console.log('On submit is working... => ', this.logInForm.invalid);

    this.logIn();
  }

  logIn(): void {
    const loginCredentials = {
      citizenId: this.logInForm.value.citizenId,
      password: this.logInForm.value.password,
    };

    this.authService.login(loginCredentials).subscribe(
      (res) => {
        if (res) {
          console.log('logged in', res[0].citizenId);
          localStorage.setItem('citizenId', res[0].citizenId);
          this.router.navigate(['AadharApp/citizens/dashboard']);
        }
      },
      (err) => {
        if (err == 'Incorrect citizenId.') {
          this.status = 'Incorrect citizenId.';
        } else if (err == 'Incorrect password.') {
          this.status = 'Incorrect password.';
        } else {
          this.status = 'Invalid credentials.';
        }
        console.log(err);
        this.cssStringVar = 'red size20';
      }
    );

    // if (
    //   this.correctEmail === this.citizenId &&
    //   this.correctPassword === this.password
    // ) {
    //   this.status = 'Valid Credentials';
    //   this.isDisabled = false;
    //   this.router.navigate(['/employees']);
    // } else {
    //   this.status = 'Invalid Credentials';
    //   this.isDisabled = true;
    //   this.cssStringVar = 'red size20';
    // }

    this.citizenId = '';
    this.password = '';
  }

  loginAdmin(): void {
    this.router.navigate(['AadharApp/admin/logIn']);
  }

  logout(): void {
    this.isDisabled = true;
  }
}
