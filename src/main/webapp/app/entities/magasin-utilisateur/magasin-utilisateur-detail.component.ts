import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMagasinUtilisateur } from 'app/shared/model/magasin-utilisateur.model';

@Component({
  selector: 'jhi-magasin-utilisateur-detail',
  templateUrl: './magasin-utilisateur-detail.component.html',
})
export class MagasinUtilisateurDetailComponent implements OnInit {
  magasinUtilisateur: IMagasinUtilisateur | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ magasinUtilisateur }) => (this.magasinUtilisateur = magasinUtilisateur));
  }

  previousState(): void {
    window.history.back();
  }
}
