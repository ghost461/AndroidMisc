package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.RequiresApi;

@RequiresApi(value=21) class TreeDocumentFile extends DocumentFile {
    private Context mContext;
    private Uri mUri;

    TreeDocumentFile(DocumentFile arg1, Context arg2, Uri arg3) {
        super(arg1);
        this.mContext = arg2;
        this.mUri = arg3;
    }

    public boolean canRead() {
        return DocumentsContractApi19.canRead(this.mContext, this.mUri);
    }

    public boolean canWrite() {
        return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
    }

    public DocumentFile createDirectory(String arg3) {
        TreeDocumentFile v0;
        Uri v3 = DocumentsContractApi21.createDirectory(this.mContext, this.mUri, arg3);
        if(v3 != null) {
            v0 = new TreeDocumentFile(((DocumentFile)this), this.mContext, v3);
        }
        else {
            DocumentFile v0_1 = null;
        }

        return ((DocumentFile)v0);
    }

    public DocumentFile createFile(String arg3, String arg4) {
        DocumentFile v4_1;
        Uri v3 = DocumentsContractApi21.createFile(this.mContext, this.mUri, arg3, arg4);
        if(v3 != null) {
            TreeDocumentFile v4 = new TreeDocumentFile(((DocumentFile)this), this.mContext, v3);
        }
        else {
            v4_1 = null;
        }

        return v4_1;
    }

    public boolean delete() {
        return DocumentsContractApi19.delete(this.mContext, this.mUri);
    }

    public boolean exists() {
        return DocumentsContractApi19.exists(this.mContext, this.mUri);
    }

    public String getName() {
        return DocumentsContractApi19.getName(this.mContext, this.mUri);
    }

    public String getType() {
        return DocumentsContractApi19.getType(this.mContext, this.mUri);
    }

    public Uri getUri() {
        return this.mUri;
    }

    public boolean isDirectory() {
        return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
    }

    public boolean isFile() {
        return DocumentsContractApi19.isFile(this.mContext, this.mUri);
    }

    public boolean isVirtual() {
        return DocumentsContractApi19.isVirtual(this.mContext, this.mUri);
    }

    public long lastModified() {
        return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
    }

    public long length() {
        return DocumentsContractApi19.length(this.mContext, this.mUri);
    }

    public DocumentFile[] listFiles() {
        Uri[] v0 = DocumentsContractApi21.listFiles(this.mContext, this.mUri);
        DocumentFile[] v1 = new DocumentFile[v0.length];
        int v2;
        for(v2 = 0; v2 < v0.length; ++v2) {
            v1[v2] = new TreeDocumentFile(((DocumentFile)this), this.mContext, v0[v2]);
        }

        return v1;
    }

    public boolean renameTo(String arg3) {
        Uri v3 = DocumentsContractApi21.renameTo(this.mContext, this.mUri, arg3);
        if(v3 != null) {
            this.mUri = v3;
            return 1;
        }

        return 0;
    }
}

