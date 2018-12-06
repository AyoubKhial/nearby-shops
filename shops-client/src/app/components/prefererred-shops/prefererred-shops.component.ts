import { Component, OnInit } from '@angular/core';
import { ShopsService } from 'src/app/services/shops.service';
import { Shop } from '../../models/shop';
import { Subject } from 'rxjs';
import { takeUntil } from "rxjs/operators";

@Component({
    selector: 'app-prefererred-shops',
    templateUrl: './prefererred-shops.component.html',
    styleUrls: ['./prefererred-shops.component.css']
})
export class PrefererredShopsComponent implements OnInit {

    public shops: Shop[] = []
    private unsubscribe = new Subject<void>();

    constructor(private shopsService: ShopsService) { }

    ngOnInit() {
        this.getPreferredShops();
    }

    getPreferredShops() {
        this.shopsService.getPreferredShops().pipe(takeUntil(this.unsubscribe)).subscribe(response => {
            this.shops = response.content;
        });
    }

    removeShopeFromPreferred(shopId: string) {
        this.shopsService.removeShopeFromPreferred(shopId).subscribe(response => {
            if(response.code == 201) {
                this.getPreferredShops();
            }
        });
    }

}
