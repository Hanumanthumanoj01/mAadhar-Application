import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css'],
})
export class AdminLoginComponent implements OnInit {
  loginId: string = '';
  password: string = '';

  correctLoginId: string = '987123987123';
  correctPassword: string = 'Abcd@1234';

  status: string = '';
  isDisabled = true;
  cssStringVar: string = '';
  isInvalidCredentials: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    public authService: AuthService
  ) {}

  submitted = false;

  adminLogInForm: FormGroup = new FormGroup({});

  ngOnInit(): void {
    this.adminLogInForm = this.formBuilder.group({
      loginId: ['', [Validators.required]],
      password: [
        '',
        [
          Validators.required,
          // Validators.pattern(
          //   '^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$)$'
          // ),
        ],
      ],
    });
  }

  get f() {
    return this.adminLogInForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.adminLogInForm.invalid) {
      return;
    }
    console.log('On submit is working... => ', this.adminLogInForm.invalid);

    this.logIn();
  }

  logIn(): void {
    const loginCredentials = {
      citizenId: this.adminLogInForm.value.citizenId,
      password: this.adminLogInForm.value.password,
    };

    if (
      this.correctLoginId == this.adminLogInForm.value.loginId &&
      this.correctPassword == this.adminLogInForm.value.password
    ) {
      localStorage.setItem('adminId', this.correctLoginId);
      this.router.navigate(['AadharApp/admin/dashboard']);
    } else {
      this.status = 'Invalid Credentials';
      this.isDisabled = true;
      this.cssStringVar = 'red size20';

      console.log('not signed in');
    }

    this.loginId = '';
    this.password = '';
  }

  loginUser(): void {
    this.router.navigate(['AadharApp/citizen/logIn']);
  }

  logout(): void {
    this.isDisabled = true;
  }
}
