package rhykee.solver.task16;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OperationalPacket extends Packet {

    private final int lengthTypeId;
    private final List<Packet> packets = new ArrayList<>();
    private final Length length = new Length();

    public OperationalPacket(Packet packet, int lengthTypeId) {
        super(packet.getVersionNumber(), packet.getTypeId());
        this.lengthTypeId = lengthTypeId;
    }

    public int getLength() {
        if (lengthTypeId == 0) {
            return length.getTotalLength();
        } else {
            return length.getNumberOfSubPackets();
        }
    }

    public void setLength(int length) {
        if (lengthTypeId == 0) {
            this.length.setTotalLength(length);
        } else {
            this.length.setNumberOfSubPackets(length);
        }
    }

    public int getLengthBitCount() {
        if (lengthTypeId == 0) {
            return 15;
        } else {
            return 11;
        }
    }

    @Data
    private static class Length {
        private int totalLength;
        private int numberOfSubPackets;
    }
}
