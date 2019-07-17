import { CommonModule, DatePipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ErrorHandler, LOCALE_ID, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbActiveModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxMaskModule } from 'ngx-mask';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ApplicationErrorHandler } from './app.error-handler';
import { ColaboradorFormComponent } from './colaboradores/colaborador-form/colaborador-form.component';
import { ColaboradorListComponent } from './colaboradores/colaborador-list/colaborador-list.component';
import { InputContainerComponent } from './shared/input-container/input-container.component';
import { ModalErrorComponent } from './shared/modal-error/modal-error.component';

// export const options: Partial<IConfig> | (() => Partial<IConfig>);

@NgModule({
  declarations: [AppComponent, InputContainerComponent, ModalErrorComponent, ColaboradorListComponent, ColaboradorFormComponent],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    NgxMaskModule.forRoot()
  ],
  providers: [
    NgbActiveModal,
    DatePipe,
    { provide: LOCALE_ID, useValue: 'pt-BR' },
    { provide: ErrorHandler, useClass: ApplicationErrorHandler }
  ],
  bootstrap: [AppComponent],
  entryComponents: [ModalErrorComponent]
})
export class AppModule {}
