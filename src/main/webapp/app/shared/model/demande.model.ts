import { Moment } from 'moment';
import { IDemandeInfo } from 'app/shared/model/demande-info.model';
import { IPieceJoindre } from 'app/shared/model/piece-joindre.model';
import { IMagasin } from 'app/shared/model/magasin.model';
import { IFournisseur } from 'app/shared/model/fournisseur.model';

export interface IDemande {
  id?: number;
  numero?: number;
  rtr?: string;
  rtrLibelle?: string;
  objet?: string;
  description?: string;
  dateBesion?: Moment;
  dateCreation?: Moment;
  createur?: string;
  budget?: number;
  affectationLibelle?: string;
  dateAffictation?: Moment;
  moreInformation?: string;
  etat?: string;
  messageValidation?: string;
  messageClouture?: string;
  dateClouture?: Moment;
  meilleurPrixMagasin?: number;
  prixNegocie?: number;
  demandeInfos?: IDemandeInfo[];
  pieceJoindres?: IPieceJoindre[];
  magasin?: IMagasin;
  fournisseurMagasin?: IFournisseur;
  fournisseurFinal?: IFournisseur;
}

export class Demande implements IDemande {
  constructor(
    public id?: number,
    public numero?: number,
    public rtr?: string,
    public rtrLibelle?: string,
    public objet?: string,
    public description?: string,
    public dateBesion?: Moment,
    public dateCreation?: Moment,
    public createur?: string,
    public budget?: number,
    public affectationLibelle?: string,
    public dateAffictation?: Moment,
    public moreInformation?: string,
    public etat?: string,
    public messageValidation?: string,
    public messageClouture?: string,
    public dateClouture?: Moment,
    public meilleurPrixMagasin?: number,
    public prixNegocie?: number,
    public demandeInfos?: IDemandeInfo[],
    public pieceJoindres?: IPieceJoindre[],
    public magasin?: IMagasin,
    public fournisseurMagasin?: IFournisseur,
    public fournisseurFinal?: IFournisseur
  ) {}
}
