import { Component, OnInit, OnDestroy } from '@angular/core';
import { ShopsService } from 'src/app/services/shops.service';
import { Shop } from '../../models/shop';
import { Subject } from 'rxjs';
import { takeUntil } from "rxjs/operators";

@Component({
    selector: 'app-prefererred-shops',
    templateUrl: './prefererred-shops.component.html',
    styleUrls: ['./prefererred-shops.component.css']
})
export class PrefererredShopsComponent implements OnInit, OnDestroy {

    public shops: Shop[] = []
    private unsubscribe = new Subject<void>();

    constructor(private shopsService: ShopsService) { }

    ngOnInit(): void {
        this.getPreferredShops();
    }

    // get the preferred shops of the current user
    getPreferredShops(): void {
        this.shopsService.getPreferredShops().pipe(takeUntil(this.unsubscribe)).subscribe(response => {
            this.shops = response.content;
        });
    }

    // get a shop from the preferred shops list of the current user
    removeShopeFromPreferred(shopId: string): void {
        this.shopsService.removeShopeFromPreferred(shopId).pipe(takeUntil(this.unsubscribe)).subscribe(response => {
            if(response.code == 201) {
                this.getPreferredShops();
            }
        });
    }

    // unsubscribing from observable subscriptions 
    ngOnDestroy(): void {
        this.unsubscribe.next();
        this.unsubscribe.complete();
    }
}
