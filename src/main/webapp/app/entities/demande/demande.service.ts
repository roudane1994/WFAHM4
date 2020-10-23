import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDemande } from 'app/shared/model/demande.model';

type EntityResponseType = HttpResponse<IDemande>;
type EntityArrayResponseType = HttpResponse<IDemande[]>;

@Injectable({ providedIn: 'root' })
export class DemandeService {
  public resourceUrl = SERVER_API_URL + 'api/demandes';

  constructor(protected http: HttpClient) {}

  create(demande: IDemande): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(demande);
    return this.http
      .post<IDemande>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(demande: IDemande): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(demande);
    return this.http
      .put<IDemande>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDemande>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDemande[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(demande: IDemande): IDemande {
    const copy: IDemande = Object.assign({}, demande, {
      dateBesion: demande.dateBesion && demande.dateBesion.isValid() ? demande.dateBesion.format(DATE_FORMAT) : undefined,
      dateCreation: demande.dateCreation && demande.dateCreation.isValid() ? demande.dateCreation.format(DATE_FORMAT) : undefined,
      dateAffictation:
        demande.dateAffictation && demande.dateAffictation.isValid() ? demande.dateAffictation.format(DATE_FORMAT) : undefined,
      dateClouture: demande.dateClouture && demande.dateClouture.isValid() ? demande.dateClouture.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateBesion = res.body.dateBesion ? moment(res.body.dateBesion) : undefined;
      res.body.dateCreation = res.body.dateCreation ? moment(res.body.dateCreation) : undefined;
      res.body.dateAffictation = res.body.dateAffictation ? moment(res.body.dateAffictation) : undefined;
      res.body.dateClouture = res.body.dateClouture ? moment(res.body.dateClouture) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((demande: IDemande) => {
        demande.dateBesion = demande.dateBesion ? moment(demande.dateBesion) : undefined;
        demande.dateCreation = demande.dateCreation ? moment(demande.dateCreation) : undefined;
        demande.dateAffictation = demande.dateAffictation ? moment(demande.dateAffictation) : undefined;
        demande.dateClouture = demande.dateClouture ? moment(demande.dateClouture) : undefined;
      });
    }
    return res;
  }
}
