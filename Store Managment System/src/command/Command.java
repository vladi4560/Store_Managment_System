package command;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Command {
void excute() throws FileNotFoundException, IOException;
}
