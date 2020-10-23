import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';

type EntityResponseType = HttpResponse<IMagasinUtilisateur>;
type EntityArrayResponseType = HttpResponse<IMagasinUtilisateur[]>;

@Injectable({ providedIn: 'root' })
export class MagasinUtilisateurService {
  public resourceUrl = SERVER_API_URL + 'api/magasin-utilisateurs';

  constructor(protected http: HttpClient) {}

  create(magasinUtilisateur: IMagasinUtilisateur): Observable<EntityResponseType> {
    return this.http.post<IMagasinUtilisateur>(this.resourceUrl, magasinUtilisateur, { observe: 'response' });
  }

  update(magasinUtilisateur: IMagasinUtilisateur): Observable<EntityResponseType> {
    return this.http.put<IMagasinUtilisateur>(this.resourceUrl, magasinUtilisateur, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMagasinUtilisateur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMagasinUtilisateur[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
