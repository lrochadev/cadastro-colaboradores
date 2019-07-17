import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-error',
  templateUrl: './modal-error.component.html'
})
export class ModalErrorComponent {
  errorDetails: any = {};
  title: string;
  mensagem: string;
  constructor(public ngbActiveModal: NgbActiveModal) {}
}
