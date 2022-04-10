import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { finalize } from 'rxjs';
import { Usuario } from '../../models/Usuario';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formularioUser: FormGroup;

  constructor(private router: Router,
    private fb: FormBuilder,
    private authStorage: Storage,
    private authService: LoginService,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService) {

    this.formularioUser = this.fb.group({
      fbUsuario: [{ value: '', disabled: false }],
      fbClave: [{ value: '', disabled: false }]
    });

  }

  ngOnInit(): void {
  }

  onLogin() {
    if (this.formularioUser.get('fbUsuario')?.value === undefined ||
      this.formularioUser.get('fbUsuario')?.value === null ) {
      this.toastr.warning('Advertencia', 'Ingresar un usuario, no puede estar vacio');
      return;
    }

    if (this.formularioUser.get('fbClave')?.value === undefined ||
      this.formularioUser.get('fbClave')?.value === null ) {
      this.toastr.warning('Advertencia', 'Ingresar una contraseña, no puede estar vacio');
      return;
    }

    const user: Usuario = {
      user: this.formularioUser.get('fbUsuario')?.value,
      password: this.formularioUser.get('fbClave')?.value
    }

    this.spinner.show('spinner-capacitacion');
    this.authService.loginSecurity(user).pipe(finalize(() => this.spinner.hide('spinner-capacitacion')))
    .subscribe(authResponse => {
      console.log('datos consultados del usuario' + authResponse);
    });
  }

}
