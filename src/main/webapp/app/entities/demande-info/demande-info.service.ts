import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDemandeInfo } from 'app/shared/model/demande-info.model';

type EntityResponseType = HttpResponse<IDemandeInfo>;
type EntityArrayResponseType = HttpResponse<IDemandeInfo[]>;

@Injectable({ providedIn: 'root' })
export class DemandeInfoService {
  public resourceUrl = SERVER_API_URL + 'api/demande-infos';

  constructor(protected http: HttpClient) {}

  create(demandeInfo: IDemandeInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(demandeInfo);
    return this.http
      .post<IDemandeInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(demandeInfo: IDemandeInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(demandeInfo);
    return this.http
      .put<IDemandeInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDemandeInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDemandeInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(demandeInfo: IDemandeInfo): IDemandeInfo {
    const copy: IDemandeInfo = Object.assign({}, demandeInfo, {
      dateDemande: demandeInfo.dateDemande && demandeInfo.dateDemande.isValid() ? demandeInfo.dateDemande.format(DATE_FORMAT) : undefined,
      dateReponse: demandeInfo.dateReponse && demandeInfo.dateReponse.isValid() ? demandeInfo.dateReponse.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateDemande = res.body.dateDemande ? moment(res.body.dateDemande) : undefined;
      res.body.dateReponse = res.body.dateReponse ? moment(res.body.dateReponse) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((demandeInfo: IDemandeInfo) => {
        demandeInfo.dateDemande = demandeInfo.dateDemande ? moment(demandeInfo.dateDemande) : undefined;
        demandeInfo.dateReponse = demandeInfo.dateReponse ? moment(demandeInfo.dateReponse) : undefined;
      });
    }
    return res;
  }
}
