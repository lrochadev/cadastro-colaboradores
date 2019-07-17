import { Injectable } from '@angular/core';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';

import { ModalErrorComponent } from './modal-error.component';

@Injectable({
  providedIn: 'root'
})
export class ModalService {
  constructor(private modalNgbService: NgbModal) {}

  exibirModal(mensagem?: string, errorDetails?: any) {
    const options: NgbModalOptions = { size: 'lg' };
    const initialState = { mensagem, errorDetails };
    let template: any;

    // componentInstance: The instance of component used as modal's content.
    // Undefined when a TemplateRef is used as modal's content. (Type: any)
    template = this.modalNgbService.open(ModalErrorComponent, options).componentInstance;
    template.mensagem = initialState.mensagem;
    template.errorDetails = initialState.errorDetails;
  }
}
