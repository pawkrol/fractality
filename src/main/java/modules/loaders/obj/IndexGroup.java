package modules.loaders.obj;

class IndexGroup {

    private int vi;
    private int vti;
    private int vni;

    IndexGroup(int vi, int vti, int vni) {
        this.vi = vi;
        this.vti = vti;
        this.vni = vni;
    }

    public int getVi() {
        return vi;
    }

    public int getVti() {
        return vti;
    }

    public int getVni() {
        return vni;
    }
}
