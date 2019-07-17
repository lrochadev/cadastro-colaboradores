import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { from } from 'rxjs';
import { catchError, debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { ModalService } from 'src/app/shared/modal-error/modal.service';

import { Colaborador } from '../colaborador.model';
import { ColaboradorService } from '../colaborador.service';

@Component({
  selector: 'app-colaborador-list',
  templateUrl: './colaborador-list.component.html',
  styleUrls: ['./colaborador-list.component.css']
})
export class ColaboradorListComponent implements OnInit {
  colaboradores: Colaborador[];
  searchForm: FormGroup;

  constructor(private fb: FormBuilder, private colaboradorService: ColaboradorService, private genericModalService: ModalService) {}

  ngOnInit() {
    this.searchForm = this.fb.group({
      searchControl: this.fb.control('')
    });

    this.searchForm.controls.searchControl.valueChanges
      .pipe(
        debounceTime(500),
        distinctUntilChanged(),
        switchMap(termoDaBusca =>
          this.colaboradorService[!termoDaBusca.trim() ? 'listar' : 'buscarPorNome'](termoDaBusca).pipe(catchError(() => from([])))
        )
      )
      .subscribe(colaboradores => (this.colaboradores = colaboradores));

    this.listar();
  }

  private listar() {
    this.colaboradorService.listar().subscribe(data => (this.colaboradores = data));
  }

  deletar(colaborador: Colaborador) {
    if (confirm('Deseja realmente excluir este item?')) {
      this.colaboradorService
        .deletar(colaborador.id)
        .subscribe(
          () => (this.colaboradores = this.colaboradores.filter(element => element !== colaborador)),
          () => alert('Erro ao tentar excluir!')
        );
    }
  }
}
