import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-user-sign-up',
  templateUrl: './user-sign-up.component.html',
  styleUrls: ['./user-sign-up.component.css'],
})
export class UserSignUpComponent implements OnInit {
  userFullName: string = '';
  email: string = '';
  gender: string = '';
  mobile: string = '';
  address: string = '';
  status: string = '';
  isDisabled = true;
  cssStringVar: string = '';

  signUpForm: FormGroup = new FormGroup({});

  constructor(
    private router: Router,
    public authService: AuthService,
    private formBuilder: FormBuilder
  ) {}

  submitted = false;

  ngOnInit(): void {
    this.signUpForm = this.formBuilder.group({
      userFullName: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      mobile: [
        '',
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(10),
          Validators.min(999999999),
          Validators.max(9999999999),
        ],
      ],
      gender: ['', [Validators.required]],
      address: [
        '',
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(50),
        ],
      ],
    });
  }

  get c() {
    return this.signUpForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.signUpForm.invalid) {
      return;
    }
    //console.log('On submit is working... => ', this.signUpForm.invalid);

    this.signUp();
  }

  signUp(): void {
    const signUpDetails = {
      userFullName: this.signUpForm.value.userFullName,
      email: this.signUpForm.value.email,
      gender: this.signUpForm.value.gender,
      mobile: this.signUpForm.value.mobile,
      address: this.signUpForm.value.address,
    };
    this.authService.signUp(signUpDetails).subscribe(
      (res) => {
        if (res) {
          localStorage.setItem('citizenId', res.citizenId);

          //console.log('SignedUp', res);
          this.router.navigate(['AadharApp/citizens/dashboard']);
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
