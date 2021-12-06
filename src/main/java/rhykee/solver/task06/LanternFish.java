package rhykee.solver.task06;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanternFish {

    private int timer = 8;

    public boolean age() {
        if (timer == 0) {
            timer = 6;
            return true;
        }
        timer--;
        return false;
    }


}
