package rhykee.solver.task16;


public class LiteralPacket extends Packet {

    public LiteralPacket(Packet packet, long value) {
        super(packet.getVersionNumber(), packet.getTypeId());
        setValue(value);
    }

}
