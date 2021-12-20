import java.util.*;

public class Packet {
    private final int version;
    private final int typeID;
    private int length;
    private long value;

    // Only for non-literal packets
    private final List<Packet> packets = new ArrayList<>();

    public Packet(String input, boolean isBinary) {
        if (!isBinary) {
            input = hexToBinary(input);
        }
        version = Integer.parseInt(input.substring(0, 3), 2);
        typeID = Integer.parseInt(input.substring(3, 6), 2);
        boolean literal = typeID == 4;
        if (literal) {
            length = 6;
            value = parseGroups(input.substring(6));
        } else  {
            int lengthTypeID = Integer.parseInt(input.substring(6, 7));
            int subPacketLength;
            if (lengthTypeID == 0) {
                subPacketLength = Integer.parseInt(input.substring(7, 22), 2);
                int currentLength = 0;
                Packet subPack = new Packet(input.substring(22), true);
                packets.add(subPack);
                currentLength += subPack.getLength();
                while (currentLength < subPacketLength) {
                    subPack = new Packet(input.substring(22 + currentLength), true);
                    packets.add(subPack);
                    currentLength += subPack.getLength();
                }
                length = 22 + currentLength;
            } else {
                subPacketLength = Integer.parseInt(input.substring(7, 18), 2);
                int currentLength = 0;
                int numOfSubPackets = 1;
                Packet subPack = new Packet(input.substring(18), true);
                packets.add(subPack);
                currentLength += subPack.getLength();
                while (numOfSubPackets < subPacketLength) {
                    subPack = new Packet(input.substring(18 + currentLength), true);
                    packets.add(subPack);
                    currentLength += subPack.getLength();
                    numOfSubPackets++;
                }
                length = 18 + currentLength;
            }
        }
    }

    public int versionSum() {
        int sum = 0;
        for (Packet pack : packets) {
            sum += pack.versionSum();
        }
        sum += version;
        return sum;
    }

    public int getLength() {
        return length;
    }

    public long parseGroups(String groups) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(groups.charAt(i) == '1') {
            sb.append(groups.substring(i + 1, i + 5));
            i += 5;
        }
        sb.append(groups.substring(i + 1, i+ 5));
        i += 5;
        length += i;
        return Long.parseLong(sb.toString(), 2);
    }

    public long getValue() {
        switch (typeID) {
            case 0:
                return sumValue();
            case 1:
                return productValue();
            case 2:
                return minimumValue();
            case 3:
                return maximumValue();
            case 4:
                return value;
            case 5:
                return greaterThanValue();
            case 6:
                return lessThanValue();
            case 7:
                return equalToValue();
        }
        return 0;
    }

    private long equalToValue() {
        return packets.get(0).getValue() == packets.get(1).getValue() ? 1 : 0;
    }

    private long lessThanValue() {
        return packets.get(0).getValue() < packets.get(1).getValue() ? 1 : 0;
    }

    private long greaterThanValue() {
        return packets.get(0).getValue() > packets.get(1).getValue() ? 1 : 0;
    }

    private long maximumValue() {
        long result = Long.MIN_VALUE;
        for (Packet pack : packets) {
            if (result < pack.getValue()) {
                result = pack.getValue();
            }
        }
        return result;
    }

    private long minimumValue() {
        long result = Long.MAX_VALUE;
        for (Packet pack : packets) {
            if (result > pack.getValue()) {
                result = pack.getValue();
            }
        }
        return result;
    }

    private long productValue() {
        long result = 1;
        for (Packet pack : packets) {
            result *= pack.getValue();
        }
        return result;
    }

    private long sumValue() {
        long result = 0;
        for (Packet pack : packets) {
            result += pack.getValue();
        }
        return result;
    }

    String hexToBinary(String hex) {
        String binary = "";

        hex = hex.toUpperCase();

        Map<Character, String> hashMap
                = new HashMap<>();

        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        int i;
        char ch;

        for (i = 0; i < hex.length(); i++) {
            ch = hex.charAt(i);
            if (hashMap.containsKey(ch))
                binary += hashMap.get(ch);
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }
        return binary;
    }
}
