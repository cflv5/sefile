package tr.edu.yildiz.ce.sefile.domain.io;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;

import tr.edu.yildiz.ce.sefile.domain.entity.File;

public class FileResource extends ByteArrayResource {
    private final File resouceFile;

    public FileResource(File resouceFile) {
        super(resouceFile.getContent());
        this.resouceFile = resouceFile;
    }

    public File getResouceFile() {
        return resouceFile;
    }

    public MediaType getContentType() {
        return MediaType.parseMediaType(resouceFile.getContentType());
    }

    @Override
    public String getFilename() {
        return resouceFile.getName();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((resouceFile == null) ? 0 : resouceFile.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        FileResource other = (FileResource) obj;
        if (resouceFile == null) {
            if (other.resouceFile != null)
                return false;
        } else if (!resouceFile.equals(other.resouceFile))
            return false;
        return true;
    }

}
