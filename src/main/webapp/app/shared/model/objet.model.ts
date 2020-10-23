export interface IObjet {
  id?: number;
  objet?: string;
}

export class Objet implements IObjet {
  constructor(public id?: number, public objet?: string) {}
}
