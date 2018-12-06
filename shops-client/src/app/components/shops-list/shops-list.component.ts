import { Component, OnInit, OnDestroy } from '@angular/core';
import { ShopsService } from 'src/app/services/shops.service';
import { Shop } from '../../models/shop';
import { Subject } from 'rxjs';
import { takeUntil } from "rxjs/operators";

@Component({
    selector: 'app-shops-list',
    templateUrl: './shops-list.component.html',
    styleUrls: ['./shops-list.component.css']
})
export class ShopsListComponent implements OnInit, OnDestroy {

    public shops: Shop[] = [];
    private unsubscribe = new Subject<void>();

    constructor(private shopsService: ShopsService) { }

    ngOnInit() {
        this.listShopsSortedByDistance();
    }

    listShopsSortedByDistance() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                this.shopsService.getAllShops(position.coords).pipe(takeUntil(this.unsubscribe)).subscribe(response => {
                    this.shopsService.getPreferredShops().subscribe(response2 => {
                        var toRemove = response2.content;
                        this.shops = response.content;
                        for(var i = this.shops.length - 1; i >= 0; i--){
                            for(var j = 0; j < toRemove.length; j++){
                                if(this.shops[i] && (this.shops[i].name === toRemove[j].name)){
                                    this.shops.splice(i, 1);
                               }
                           }
                       }
                    })
                });
            });
        } else {
            alert("Geolocation is not supported by this browser.");
        }
    }

    addShopToPreferred(shopId: string) {
        this.shopsService.addShopToPreferred(shopId).subscribe(response => {
            if(response.code == 201) {
                this.listShopsSortedByDistance();
            }
        });
    }

    addShopToDisliked(shopId: string) {
        this.shopsService.addShopToDisliked(shopId).subscribe();
    }

    // unsubscribing from observable subscriptions 
    ngOnDestroy(): void {
        this.unsubscribe.next();
        this.unsubscribe.complete();
    }
}
