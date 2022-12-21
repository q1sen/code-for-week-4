package klaeffer.service;

import java.util.List;

public class KlaeffPage{
    public List<KlaeffDetail> getKlaeffs() {
        return klaeffs;
    }

    public boolean isMore() {
        return more;
    }

    private List<KlaeffDetail> klaeffs;
    private boolean more;


    public KlaeffPage(List<KlaeffDetail> klaeffs, boolean more) {
        this.klaeffs = klaeffs;
        this.more = more;
    }
}
