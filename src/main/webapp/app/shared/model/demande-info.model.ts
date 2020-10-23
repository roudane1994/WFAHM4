import { Moment } from 'moment';
import { IDemande } from 'app/shared/model/demande.model';

export interface IDemandeInfo {
  id?: number;
  nordre?: number;
  infoDemande?: string;
  reponse?: string;
  dateDemande?: Moment;
  dateReponse?: Moment;
  demande?: IDemande;
}

export class DemandeInfo implements IDemandeInfo {
  constructor(
    public id?: number,
    public nordre?: number,
    public infoDemande?: string,
    public reponse?: string,
    public dateDemande?: Moment,
    public dateReponse?: Moment,
    public demande?: IDemande
  ) {}
}
