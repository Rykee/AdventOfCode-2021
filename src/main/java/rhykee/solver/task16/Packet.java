package rhykee.solver.task16;

import lombok.Data;

@Data
public class Packet {

    protected final int versionNumber;
    protected final int typeId;
    protected long value;

}
