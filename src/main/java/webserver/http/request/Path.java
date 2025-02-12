package webserver.http.request;

import java.util.List;
import java.util.Optional;

public class Path {
    public static final String PATH_DELIMITER = "/";
    private final List<String> subPaths;
    private final Parameters queryParams;

    public Path(String path, Parameters queryParams) {
        this.subPaths = List.of(path.split(PATH_DELIMITER));
        this.queryParams = queryParams;
    }

    public int getDepth() {
        return subPaths.size();
    }

    public Optional<String> getSubPathByDepth(int depth) {
        return (depth < 0 || depth >= getDepth()) ? Optional.empty() : Optional.ofNullable(subPaths.get(depth));
    }

    public int getDepthOfSubPath(String part) {
        int depth = subPaths.indexOf(part);
        if (depth < 0) {
            throw new IllegalArgumentException("잘못된 부분 경로입니다.");
        }
        return depth;
    }

    public Parameters getQueries() {
        return queryParams;
    }

    @Override
    public String toString() {
        return String.join(PATH_DELIMITER, subPaths);
    }
}
