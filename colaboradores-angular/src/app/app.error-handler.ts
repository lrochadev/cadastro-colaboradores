import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandler, Injectable, NgZone } from '@angular/core';

import { ModalService } from './shared/modal-error/modal.service';

@Injectable()
export class ApplicationErrorHandler extends ErrorHandler {
  constructor(private zone: NgZone, private genericModalService: ModalService) {
    super();
  }

  handleError(errorResponse: HttpErrorResponse | any) {
    if (errorResponse instanceof HttpErrorResponse) {
      this.zone.run(() => {
        const errorDetails = createListErrors(errorResponse);
        this.genericModalService.exibirModal('', errorDetails);
      });
      super.handleError(errorResponse);
    }
  }
}
function createListErrors(errorResponse: HttpErrorResponse) {
  let errorDetails: any = {};
  const defaultErrorObject = { titulo: 'Sistema temporariamente indisponível. Tente novamente mais tarde.' };

  if (errorResponse.status !== 0) {
    errorDetails = errorResponse.error
      ? {
          titulo: errorResponse.error.title,
          status: errorResponse.error.status,
          detalhes: errorResponse.error.detail,
          developer: errorResponse.error.developerMessage
        }
      : defaultErrorObject;
  } else {
    errorDetails = defaultErrorObject;
  }

  return errorDetails;
}
