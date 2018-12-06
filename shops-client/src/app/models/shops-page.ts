import { Shop } from "./shop";

export class ShopsPage {
    public content: Array<Shop>;
    public page: number;
    public size: number;
    public totalElements: number;
    public totalPages: number;
    public last: boolean;
}