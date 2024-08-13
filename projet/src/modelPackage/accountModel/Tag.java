package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Tag {
    private int tag;

    public Tag(int tag) throws IllegalAccountArgumentException{
        setTag(tag);
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(int tag) throws IllegalAccountArgumentException {
        if (tag < 0) {
            throw new IllegalAccountArgumentException("Tag doit être positif");
        }
        this.tag = tag;
    }

}
