import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { ModalService } from 'src/app/shared/modal-error/modal.service';

import { Colaborador } from '../colaborador.model';
import { ColaboradorService } from '../colaborador.service';

@Component({
  selector: 'app-colaborador-form',
  templateUrl: './colaborador-form.component.html',
  styleUrls: ['./colaborador-form.component.css']
})
export class ColaboradorFormComponent implements OnInit {
  form: FormGroup;
  colaborador: Colaborador = new Colaborador();
  campoObrigatorio = 'Campo obrigatório.';
  emailPattern = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

  constructor(
    private fb: FormBuilder,
    private colaboradorService: ColaboradorService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private genericModalService: ModalService
  ) {}

  ngOnInit() {
    if (this.activatedRoute.snapshot.paramMap.get('id')) {
      this.activatedRoute.paramMap.pipe(switchMap(params => this.colaboradorService.buscarPorId(+params.get('id')))).subscribe(
        colaborador => {
          this.colaborador = colaborador;
          this.form.patchValue(colaborador);
        },
        () => this.genericModalService.exibirModal('Ocorreu um erro ao buscar colaborador!')
      );
    }

    this.createForm();
  }

  private createForm() {
    this.form = this.fb.group({
      cpf: this.fb.control('', [Validators.required]),
      nome: this.fb.control('', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]),
      telefone: this.fb.control('', [Validators.required, Validators.minLength(10)]),
      email: this.fb.control('', [Validators.required, Validators.pattern(this.emailPattern)]),
      dataNascimento: this.fb.control('', [Validators.required])
    });
  }

  cadastrar() {
    const formRawValue = this.converterDataNascimento(this.form.getRawValue());
    this.colaboradorService.cadastrar(formRawValue).subscribe(
      () => {
        this.genericModalService.exibirModal('Colaborador cadastrado com sucesso.');
        this.router.navigate(['listar']);
      },
      () => {
        this.genericModalService.exibirModal('Ocorreu um erro ao cadastrar colaborador!');
      }
    );
  }

  /**
   * Refatorar para um pipe.
   */
  private converterDataNascimento(formRawValue: any) {
    const data = formRawValue.dataNascimento;
    const dia = data.substr(0, 2);
    const mes = data.substr(2, 2);
    const ano = data.substr(4, 4);

    formRawValue.dataNascimento = ano + '-' + mes + '-' + dia;

    return formRawValue;
  }
}
