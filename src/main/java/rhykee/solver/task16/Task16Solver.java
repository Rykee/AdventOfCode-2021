package rhykee.solver.task16;

import rhykee.solver.Challenge;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Integer.parseInt;

public class Task16Solver implements Challenge {

    @Override
    public void part1(List<String> lines) {
        AtomicReference<String> binaryString = new AtomicReference<>(hexToBin(lines.get(0)));
        OperationalPacket packet = (OperationalPacket) parsePacket(binaryString);
        System.out.println("Day 15 1/2: " + sumVersion(packet, new AtomicInteger(0)));
    }

    @Override
    public void part2(List<String> lines) {
        AtomicReference<String> binaryString = new AtomicReference<>(hexToBin(lines.get(0)));
        OperationalPacket packet = (OperationalPacket) parsePacket(binaryString);
        System.out.println("Day 15 2/2: " + packet.getValue());

    }

    private int sumVersion(OperationalPacket root, AtomicInteger versionSum) {
        versionSum.addAndGet(root.getVersionNumber());
        for (int i = 0; i < root.getPackets().size(); i++) {
            if (root.getPackets().get(i) instanceof OperationalPacket) {
                sumVersion((OperationalPacket) root.getPackets().get(i), versionSum);
            } else {
                versionSum.addAndGet(root.getPackets().get(i).getVersionNumber());
            }
        }
        return versionSum.get();
    }

    private Packet parsePacket(AtomicReference<String> binaryString) {
        Packet packet = initBasicPacket(binaryString);
        if (packet.getTypeId() != 4) {
            return parseOperationalPacket(packet, binaryString);
        } else {
            return parseLiteralPacket(packet, binaryString);
        }
    }

    private OperationalPacket parseOperationalPacket(Packet packet, AtomicReference<String> binaryString) {
        OperationalPacket operationalPacket = new OperationalPacket(packet, parseInt(String.valueOf(binaryString.get().charAt(0)), 2));
        binaryString.set(binaryString.get().substring(1));
        int length = Integer.parseInt(binaryString.get().substring(0, operationalPacket.getLengthBitCount()), 2);
        binaryString.set(binaryString.get().substring(operationalPacket.getLengthBitCount()));
        operationalPacket.setLength(length);
        List<Packet> packets = operationalPacket.getPackets();
        if (operationalPacket.getLengthTypeId() == 1) {
            while (packets.size() != operationalPacket.getLength()) {
                Packet subPacket = parsePacket(binaryString);
                packets.add(subPacket);
            }
        } else {
            int parsedBits = 0;
            do {
                int currentLength = binaryString.get().length();
                Packet subPacketType0 = parsePacket(binaryString);
                packets.add(subPacketType0);
                parsedBits += currentLength - binaryString.get().length();
            } while (parsedBits != length);
        }
        operationalPacket.setValue(
                switch (operationalPacket.getTypeId()) {
                    case 0 -> packets.stream().map(Packet::getValue).mapToLong(Long::longValue).sum();
                    case 1 -> packets.stream().map(Packet::getValue).reduce(1L, (aLong, aLong2) -> aLong * aLong2);
                    case 2 -> packets.stream().map(Packet::getValue).mapToLong(Long::longValue).min().getAsLong();
                    case 3 -> packets.stream().map(Packet::getValue).mapToLong(Long::longValue).max().getAsLong();
                    case 5 -> packets.get(0).getValue() > packets.get(1).getValue() ? 1 : 0;
                    case 6 -> packets.get(0).getValue() < packets.get(1).getValue() ? 1 : 0;
                    case 7 -> packets.get(0).getValue() == packets.get(1).getValue() ? 1 : 0;
                    default -> throw new RuntimeException("Where have you been operating bro");
                });
        return operationalPacket;
    }

    private Packet initBasicPacket(AtomicReference<String> binary) {
        Packet packet = new Packet(parseInt(binary.get().substring(0, 3), 2), parseInt(binary.get().substring(3, 6), 2));
        binary.set(binary.get().substring(6));
        return packet;
    }

    private LiteralPacket parseLiteralPacket(Packet packet, AtomicReference<String> binary) {
        StringBuilder binaryNumber = new StringBuilder(); //
        String part = binary.get().substring(0, 5); //10111
        while (part.charAt(0) != '0') {
            binaryNumber.append(part.substring(1)); //0111
            binary.set(binary.get().substring(5)); //1111000101
            part = binary.get().substring(0, 5);//
        }
        binaryNumber.append(part.substring(1));
        binary.set(binary.get().substring(5));
        return new LiteralPacket(packet, Long.parseLong(binaryNumber.toString(), 2));
    }

    private String hexToBin(String hex) {
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

}
