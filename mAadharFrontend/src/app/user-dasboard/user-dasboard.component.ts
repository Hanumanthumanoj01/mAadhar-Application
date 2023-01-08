import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-dasboard',
  templateUrl: './user-dasboard.component.html',
  styleUrls: ['./user-dasboard.component.css'],
})
export class UserDasboardComponent implements OnInit {
  data = { citizenId: localStorage.getItem('citizenId') };
  user: any;

  aadharApplicationForm: FormGroup = new FormGroup({});

  constructor(
    private router: Router,
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private userService: UserService
  ) {}

  submitted = false;

  ngOnInit(): void {
    if (!this.data.citizenId) {
      this.router.navigate(['AadharApp/citizens/logIn']);
      return;
    } else {
      this.authService.getUserDetails(this.data).subscribe(
        (res) => {
          if (res) {
            this.user = res[0];

            this.aadharApplicationForm = this.formBuilder.group({
              passportId: [
                '',
                [
                  Validators.required,
                  Validators.minLength(8),
                  Validators.maxLength(8),
                ],
              ],
            });

            //console.log(res);
          }
        },
        (err) => {
          console.log(err);
        }
      );
    }
  }

  applyForAadhar(): void {
    const applicationDetails = {
      citizenId: this.user.citizenId,
      passportId: this.aadharApplicationForm.value.passportId,
    };
    this.userService.applyForAadhar(applicationDetails).subscribe(
      (res) => {
        if (res) {
          console.log(res);
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }

  signOut(): void {
    localStorage.clear();
    this.router.navigate(['AadharApp/citizens/logIn']);
  }
}

// '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$';
