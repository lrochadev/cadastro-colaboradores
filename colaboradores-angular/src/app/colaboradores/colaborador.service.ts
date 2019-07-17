import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const API = 'http://localhost:8080/colaboradores';

@Injectable({
  providedIn: 'root'
})
export class ColaboradorService {
  constructor(private http: HttpClient) {}

  cadastrar(form: any): Observable<any> {
    return this.http.post(API, form);
  }

  listar(): Observable<any> {
    return this.http.get(API);
  }

  deletar(id: number): Observable<any> {
    return this.http.delete(`${API}/${id}`);
  }

  buscarPorId(id: number): Observable<any> {
    return this.http.get(`${API}/${id}`);
  }

  buscarPorNome(nome: string): Observable<any> {
    const params = new HttpParams().append('nome', nome);
    return this.http.get(`${API}/buscarPorNome/`, { params });
  }
}
