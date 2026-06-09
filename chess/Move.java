package chess;

public record Move(int fromSquare, int toSquare) {
    public Move(int fromSquare, int toSquare) {
        this.fromSquare = fromSquare;
        this.toSquare = toSquare;
    }
}
