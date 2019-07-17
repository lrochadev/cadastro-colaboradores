import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ColaboradorFormComponent } from './colaboradores/colaborador-form/colaborador-form.component';
import { ColaboradorListComponent } from './colaboradores/colaborador-list/colaborador-list.component';

const routes: Routes = [
  { path: '', component: ColaboradorFormComponent },
  { path: 'cadastrar', component: ColaboradorFormComponent },
  { path: 'listar/:id', component: ColaboradorFormComponent },
  { path: 'listar', component: ColaboradorListComponent },
  { path: '**', component: ColaboradorListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
