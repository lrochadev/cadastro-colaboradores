import { Setor } from './setor.model';

export class Colaborador {
  id: number;
  cpf: string;
  nome: string;
  telefone: string;
  email: string;
  dataNascimento: string;
  descricaoSetor: string;
  idade: number;
  setor: Setor;
}
